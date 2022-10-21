package mainFrame.menuBar.fileMenu.newObjectsMenu.newNode;

import graphView.graphNode.NodeView;
import graphView.graphPane.GraphPane;
import mainFrame.MainFrame;
import mainFrame.tabbedPane.TabbedPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewNodeDialog extends JDialog {

    private JButton create;
    private JButton cancel;
    private JPanel checkBoxPanel;
    private JPanel xFieldHolder;
    private JTextField xField;
    private JPanel yFieldHolder;
    private JTextField yField;
    private GraphPane rootGrapPane;

    private static NewNodeDialog newNodeDialog;

    public static NewNodeDialog getInstance(){
        if (newNodeDialog == null)
            newNodeDialog = new NewNodeDialog(MainFrame.getMainFrame());
        return newNodeDialog;
    }

    public NewNodeDialog(Frame owner) {
        super(owner, "Node " + TabbedPane.getInstance().getSelectedGraphPane().getGraph().getNodeNumberGenerator());
        dialogInit();
        init();
        initButtons(this);
        rootGrapPane = TabbedPane.getInstance().getSelectedGraphPane();
    }

    private void init(){

        setSize(new Dimension(210, 350));
        setLocation(450, 450);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                ((JDialog) e.getSource()).requestFocusInWindow();
            }
        });

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        //checkBoxes init
        initCheckBox();
        JScrollPane checBoxScrollPane = new JScrollPane(checkBoxPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        checBoxScrollPane.setPreferredSize(new Dimension(150, 250));
        rootPanel.add(checBoxScrollPane, BorderLayout.CENTER);

        //button panel init
        JPanel buttonHolder = new JPanel();
        BoxLayout boxLayout = new BoxLayout(buttonHolder, BoxLayout.X_AXIS);
        buttonHolder.setLayout(boxLayout);
        buttonHolder.setBorder(new EmptyBorder(5, 5, 5, 5));

        create = new JButton("Create");
        cancel = new JButton("Cancel");
        buttonHolder.add(create);
        buttonHolder.add(Box.createHorizontalStrut(40));
        buttonHolder.add(cancel);
        rootPanel.add(buttonHolder, BorderLayout.SOUTH);

        //coordinate Fields init
        JPanel coordinateFieldHolder = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(coordinateFieldHolder, BoxLayout.Y_AXIS);
        coordinateFieldHolder.setLayout(boxLayout2);
        coordinateFieldHolder.setBorder(new EmptyBorder(5, 5, 5, 5));

        initCoordinatesFields();
        coordinateFieldHolder.add(xFieldHolder);
        coordinateFieldHolder.add(Box.createVerticalStrut(40));
        coordinateFieldHolder.add(yFieldHolder);
        rootPanel.add(coordinateFieldHolder, BorderLayout.NORTH);

        add(rootPanel);
        setVisible(true);
        setAlwaysOnTop(true);
    }

    private void initCoordinatesFields() {
        xFieldHolder = new JPanel(new FlowLayout());

        xField = new JTextField();
        xField.setPreferredSize(new Dimension(60, 30));

        JLabel xName = new JLabel("X:");

        xFieldHolder.add(xName);
        xFieldHolder.add(xField);

        yFieldHolder = new JPanel(new FlowLayout());

        yField = new JTextField();
        yField.setPreferredSize(new Dimension(60, 30));

        JLabel yName = new JLabel("Y:");

        yFieldHolder.add(yName);
        yFieldHolder.add(yField);
    }

    private void initButtons(NewNodeDialog newNodeDialog){

        cancel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToNull();
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


        });

        create.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createAction(e);
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
        });

    }

    private void createAction(MouseEvent e){

        if(!rootGrapPane.equals(TabbedPane.getInstance().getSelectedGraphPane())){
            setToNull();
            return;
        }

        GraphPane currentGraphPane = TabbedPane.getInstance().getSelectedGraphPane();

        int x = Math.min(xField.getText().isEmpty() ? e.getX() : Integer.parseInt(xField.getText()), currentGraphPane.getWidth() - 50);
        int y = Math.min(yField.getText().isEmpty() ? e.getY() : Integer.parseInt(yField.getText()), currentGraphPane.getHeight() - 50);

        NodeView newNode = GraphPane.addNode(currentGraphPane, x, y);
        GraphPane.cancelSelection();

        for (Component newNodeCheckBox : checkBoxPanel.getComponents()){
            if (((NewNodeCheckBox) newNodeCheckBox).isSelected()) {
                GraphPane.setSourceNode(newNode);
                NodeView nodeView = ((NewNodeCheckBox) newNodeCheckBox).getNodeView();
                GraphPane.addEdge(nodeView);
            }
        }

        setToNull();
    }

    private void initCheckBox(){

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        GraphPane graphPane = TabbedPane.getInstance().getSelectedGraphPane();

        for (NodeView nodeView : graphPane.getNodes()){
            checkBoxPanel.add(new NewNodeCheckBox(nodeView));
        }

        int height = graphPane.getGraph().getNodes().size()*40;
        checkBoxPanel.setPreferredSize(new Dimension(150, height));

    }

    public static boolean isInstanceNull(){
        if(newNodeDialog == null)
            return true;
        return false;
    }

    public static void setToNull(){
        newNodeDialog.dispose();
        newNodeDialog = null;
    }


}
