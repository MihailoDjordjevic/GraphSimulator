package graphView.graphEdge;

import observer.ISubscriber;
import observer.NotificationType;
import project.graphModel.EdgeModel;
import graphView.graphNode.GraohNodeMouseMotionListener;
import graphView.graphNode.NodeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EdgeView extends JPanel implements ISubscriber {

    private EdgeModel edgeModel;
    private int x1, y1, x2, y2;
    private NodeView sourceNode;
    private NodeView destinationNode;
    private boolean hasPair = false;
    private EdgeView pair;

    private JTextField flowInfo;

    public EdgeView(EdgeModel edgeModel, int x1, int y1, int x2, int y2) {
        this.edgeModel = edgeModel;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.edgeModel.setEdgePane(this);

        init();
    }

    private void init(){

        setLayout(null);
        setSize(Math.max(Math.abs(x1 - x2) + 1, 80), Math.max(Math.abs(y1 - y2), 25));
        setLocation(Math.min(x1, x2), Math.min(y1, y2));
        setOpaque(false);

        flowInfo = new JTextField(edgeModel.getFlow() + "/" + edgeModel.getCapacity());
        flowInfo.setSize(80, 25);
        flowInfo.setLocation(getSize().width/2 - 40, getSize().height/2 - 6);
        flowInfo.setOpaque(false);
        flowInfo.addMouseListener(new EdgeMouseListener());
        flowInfo.addMouseMotionListener(new GraohNodeMouseMotionListener());

        flowInfo.setEditable(true);
        flowInfo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    edgeModel.setFlow(Integer.parseInt(flowInfo.getText()));
                } catch (NumberFormatException ex) {
                }
            }
        });

        add(flowInfo);
    }

    public void updateSizeAndLocation(){
        setSize(Math.max(Math.abs(x1 - x2) + 1, 80), Math.max(Math.abs(y1 - y2), 14));
        setLocation(Math.min(x1, x2) - 1, Math.min(y1, y2) - 1);
        flowInfo.setLocation(getSize().width/2 - 40, getSize().height/2 - 6);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        int xOffset = Math.min(x1, x2);
        int yOffset = Math.min(y1, y2);

        double diagonal = Math.sqrt(Math.pow(x1 - x2, 2)  + Math.pow(y1 - y2, 2));
        double sinTheta = (((double) y2-y1)/diagonal);
        double cosTheta = (((double) x1-x2)/diagonal);

        g2.drawLine(x1 - xOffset, y1 - yOffset, x2 - xOffset, y2 - yOffset);

        double arrowX = (cosTheta/sinTheta > 1 || cosTheta/sinTheta < -1) ? 15*Math.signum(cosTheta) : 15*Math.abs(cosTheta/sinTheta)*Math.signum(cosTheta);
        double arrowY = (sinTheta/cosTheta > 1 || sinTheta/cosTheta < -1) ? 15*Math.signum(sinTheta) : 15*Math.abs(sinTheta/cosTheta)*Math.signum(sinTheta);

        g2.fillOval((int) (arrowX + x2 - xOffset - 4), (int) (y2 - arrowY - yOffset - 4), 8, 8);
    }

    private void calculateArrow(){
    }

    public EdgeModel getEdgeModel() {
        return edgeModel;
    }
    public void setX1(int x1) {
        this.x1 = x1;
    }
    public void setY1(int y1) {
        this.y1 = y1;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }
    public void setEdgeModel(EdgeModel edgeModel) {
        this.edgeModel = edgeModel;
    }
    public NodeView getSourceNode() {
        return sourceNode;
    }
    public void setSourceNode(NodeView sourceNode) {
        this.sourceNode = sourceNode;
    }
    public NodeView getDestinationNode() {
        return destinationNode;
    }
    public void setDestinationNode(NodeView destinationNode) {
        this.destinationNode = destinationNode;
    }
    public int getX1() {
        return x1;
    }
    public int getY1() {
        return y1;
    }
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }
    public boolean isHasPair() {
        return hasPair;
    }
    public void setHasPair(boolean hasPair) {
        this.hasPair = hasPair;
        double diagonal = Math.sqrt(Math.pow(x1 - x2, 2)  + Math.pow(y1 - y2, 2));
        double sinTheta = (((double) y2-y1)/diagonal);
        double cosTheta = (((double) x1-x2)/diagonal);

        double sinThetaPair = -cosTheta;
        double cosThetaPair = sinTheta;

        if (hasPair) {
            x1 += (int) (cosThetaPair*11);
            x2 += (int) (cosThetaPair*11);
            y1 -= (int) (sinThetaPair*11);
            y2 -= (int) (sinThetaPair*11);


        } else {
            x1 -= (int) (cosThetaPair*11);
            x2 -= (int) (cosThetaPair*11);
            y1 += (int) (sinThetaPair*11);
            y2 += (int) (sinThetaPair*11);
        }

        updateSizeAndLocation();

        if (hasPair)
            flowInfo.setLocation(flowInfo.getX(), (int) (flowInfo.getY() + sinTheta*6));
    }
    public EdgeView getPair() {
        return pair;
    }
    public void setPair(EdgeView pair) {
        this.pair = pair;
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

    }
}
