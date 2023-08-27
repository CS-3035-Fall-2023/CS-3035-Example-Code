package cs3035unb.cs3035examplecode.ClipboardExample;

import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;

public class MyTextArea extends TextArea {
    private Clipboard clipboard = Clipboard.getSystemClipboard();
    public MyTextArea()
    {
        super();
    }
    @Override
    public void cut()
    {

        ClipboardContent clipboardContent = getMyTextAreaContent();
        clipboard.setContent(clipboardContent);
        IndexRange range = getSelection();
        deleteText(range);
        System.out.println("cut text");
    }


    @Override
    public void copy()
    {
        ClipboardContent clipboardContent = getMyTextAreaContent();
        clipboard.setContent(clipboardContent);

        System.out.println("copy");
    }
    @Override
    public void paste()
    {
        super.paste();
        System.out.println("paste");
    }


    private ClipboardContent getMyTextAreaContent() {
        final ClipboardContent content = new ClipboardContent();
        content.putString(getSelectedText());
        Font currentFont = getFont();

        double fontSize = currentFont.getSize();
        String fontFamily = currentFont.getFamily();

        boolean isBold = false;
        boolean isItalics = false;

        if (currentFont.getStyle().contains("Bold"))
            isBold = true;
        if (currentFont.getStyle().contains("Italic"))
            isItalics = true;

        String htmlString = getSelectedText();
        htmlString = "<font face='"+fontFamily+"' style='font-size: "+fontSize+"pt;'>"+htmlString+"</font>";

        if (isBold) htmlString = "<b>"+htmlString+"</b>";
        if (isItalics) htmlString = "<i>"+htmlString+"</i>";
        content.putHtml(htmlString);
        return content;
    }
}
