package utility;

public class TestDataHolder {

    private static String noteTitle;

    public static String getNoteTitle() {
        return noteTitle;
    }

    public static void setNoteTitle(String noteContent) {
        TestDataHolder.noteTitle = noteContent;
    }
}
