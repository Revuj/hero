public class StringInverter implements StringTransformer {
    private String text;

    public StringInverter(StringDrink sd) {
        this.text = sd.getText();
    }

    @Override
    public void execute() {
        StringBuffer newText = new StringBuffer();

        for(int i = 0; i < text.length(); i++) {
            newText.append(text.charAt(text.length()-i));
        }

        this.text = newText.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
