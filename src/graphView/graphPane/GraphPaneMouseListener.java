package graphView.graphPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphPaneMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

        switch (ToolbarState.toolbarstate){

            case NEW:
            case TWOWAYEDGE: {
                if (e.getClickCount() == 1 && e.getButton() == 1)
                    GraphPane.addNode((GraphPane) e.getSource(), e.getX(), e.getY());
                else if (e.getButton() == 3)
                    GraphPane.cancelSelection();
            } break;

            default: break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
