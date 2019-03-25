public class StringCaseChanger implements StringTransformer {
    private String text;

    public StringCaseChanger(StringDrink sd) {
        this.text = sd.getText();
    }


    @Override
    public void execute() {
        StringBuffer newText = new StringBuffer();

        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLowerCase(c))
                Character.toUpperCase(c);
            else
                Character.toLowerCase(c);
            newText.append(c);
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
