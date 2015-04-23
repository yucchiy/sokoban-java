package jp.co.cyberagent;

public interface Gui {
    public void init();
    public void close();
    public void refresh();

    public GuiAction getAction();
    public void putString(int x, int y, String str);
}
