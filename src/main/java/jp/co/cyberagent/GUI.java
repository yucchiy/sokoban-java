package jp.co.cyberagent;

interface Gui {
    public void init();
    public void close();
    public GuiAction getAction();
    public void refresh();
    public void putString(int x, int y, String str);
}
