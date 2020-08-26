package demo;

import com.spire.doc.*;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;
import com.zm.demo.entity.ColumnEntity;
import com.zm.demo.entity.TableEntity;
import com.zm.demo.mapper.DbMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.TreeMap;

public class AutoGenerateTest extends BaseTest{

    @Autowired
    private DbMapper dbMapper;

    public static final TreeMap<Integer, String> headerMap;

    public static final String[] header = {
            "序号",
            "数据元名称",
            "字段名",
            "数据类型",
            "数据项长度",
            "必填"
    };

    static {
        headerMap = new TreeMap<>();
        for (int i = 0; i < header.length; i++){
            headerMap.put(i, header[i]);
        }
    }

    public static final String DBNAME = "purchase";

    @Test
    public void generate(){
        handleWord(DBNAME);
    }

    public void handleWord(String dbName){
        //创建Document对象
        Document doc = new Document();
        for(TableEntity entity : dbMapper.getAllTables(dbName)) {
            Section sec = doc.addSection();
            String table_name = entity.getTableName();
            String table_comment = entity.getTableComment();
            sec.addParagraph().setText("表名："+table_name + "["+table_comment+"]");
            List<ColumnEntity> columns = dbMapper.getAllFields(dbName, table_name);
            //添加表格
            Table table = sec.addTable(true);
            table.resetCells(columns.size() + 1, header.length);
            //设置表格第一行作为表头，写入表头数组内容，并格式化表头数据
            TableRow row = table.getRows().get(0);
            row.isHeader(true);
            row.setHeightType(TableRowHeightType.Exactly);
            row.getRowFormat().setBackColor(Color.WHITE);
            for (int i = 0; i < header.length; i++) {
                row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                Paragraph p = row.getCells().get(i).addParagraph();
                p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                TextRange range1 = p.appendText(header[i]);
                range1.getCharacterFormat().setFontName("宋体");
                range1.getCharacterFormat().setFontSize(9f);
                range1.getCharacterFormat().setTextColor(Color.black);
            }
            //写入剩余组内容到表格，并格式化数据
            for (int r = 0; r < columns.size(); r++) {
                TableRow dataRow = table.getRows().get(r + 1);
                dataRow.setHeightType(TableRowHeightType.Auto);
                dataRow.getRowFormat().setBackColor(Color.white);
                ColumnEntity column = columns.get(r);
                String length = "";
                if(column.getCharLength() != null){
                    length = String.valueOf(column.getCharLength());
                }else if(column.getNumLength() != null){
                    length = String.valueOf(column.getNumLength());
                }
                String comment =  column.getColumnComment().replaceAll("[:|：|(|（|\\s].*","");
                createCell(dataRow, String.valueOf(r+1), 0);
                createCell(dataRow, comment, 1);
                createCell(dataRow, column.getColumnName(), 2);
                createCell(dataRow, translateType(column.getDataType()), 3);
                createCell(dataRow, length, 4);
                createCell(dataRow, column.getNullable().equals("NO") ? "否" : "是", 5);
            }
        }
        doc.saveToFile("D:/1.doc");
    }

    public void createCell(TableRow dataRow, String text, int index){
        TableCell cell = dataRow.getCells().get(index);
        cell.setCellWidthType(CellWidthType.Auto);
        Paragraph p = cell.addParagraph();
        p.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
        TextRange range = p.appendText(text);
        range.getCharacterFormat().setFontSize(9f);
        if(index == 2){
            range.getCharacterFormat().setFontSize(10.5f);
        }
        if(index % 2 == 0){
            range.getCharacterFormat().setFontName("Times New Roman");
        }else {
            range.getCharacterFormat().setFontName("宋体");
        }
    }

    public String translateType(String dataType){
        String translate = "";
        switch (dataType){
            case "varchar":
            case "char":
            case "text":
                translate = "字符串";break;
            case "int":
            case "bigint":
            case "tinyint":
            case "decimal":
                translate = "数字";break;
            case "datetime":
            case "date":
            case "timestamp":
                translate = "日期时间";break;
        }
        return translate;
    }
}