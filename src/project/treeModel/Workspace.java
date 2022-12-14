package project.treeModel;

import observer.NotificationType;

public class Workspace extends AbstractNode{

    private static Workspace workspace = null;

    public static Workspace getInstance(){
        if (workspace == null) {
            workspace = new Workspace(true, null);
        }
        return workspace;
    }

    public Workspace(boolean allowsChildren, Object model) {
        super(allowsChildren, model);
    }

    @Override
    public String toString() {
        return "Workspace";
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

    }
}
