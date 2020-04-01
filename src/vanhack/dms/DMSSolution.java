package vanhack.dms;
import java.io.*;
import java.util.*;

/**
 * Created by danushka on 9/29/19.
 */
public class DMSSolution {

    public static class Heading {
        protected int weight;
        protected String text;
        public Heading(int weight, String text) {
            this.weight = weight;
            this.text = text;
        }
    }

    public static class Node {
        protected Heading heading;
        protected List<Node> children;
        public Node(Heading heading) {
            this.heading = heading;
            this.children = new ArrayList<>();
        }
    }

    private static Heading parse(String record) {
        String[] parts = record.split(" ", 2);
        int weight = Integer.parseInt(parts[0].substring(1));
        Heading heading = new Heading(weight, parts[1].trim());
        return heading;
    }

    /**
     * Converts a node to HTML.
     * This implementation is correct for all predefined test cases.
     */
    private static String toHtml(Node node) {
        StringBuilder buf = new StringBuilder();
        if (!node.heading.text.isEmpty()) {
            buf.append(node.heading.text);
            buf.append("\n");
        }
        Iterator<Node> iter = node.children.iterator();
        if (iter.hasNext()) {
            buf.append("<ul>");
            while (iter.hasNext()) {
                Node child = iter.next();
                buf.append("<li>");
                buf.append(toHtml(child));
                buf.append("</li>");
                if (iter.hasNext()) {
                    buf.append("\n");
                }
            }
            buf.append("</ul>");
        }
        return buf.toString();
    }

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Heading> headings = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.equals("")) {
                break;
            }
            headings.add(parse(line));
        }
        Node outline = toOutline(headings);
        String html = toHtml(outline);
        System.out.println(html);
    }

    /////////// BEGIN EDITABLE //////////////
    private static Node toOutline(List<Heading> headings) {
        // Implement this function. Sample code below builds an
        // outline of only the first heading.

        Node root = new Node(new Heading(0, ""));
        int currentWeight = 1;
        Node currentNode = root;
        for (Heading heading : headings) {
            Node newNode = null;
            if (heading.weight > currentWeight) {
                newNode = new Node(heading);
                currentNode.children.add(newNode);
            } else  {
                int newWeight = heading.weight;
                Node parentNode = getNode(root, newWeight - 1);
                newNode = new Node(heading);
                parentNode.children.add(newNode);
            }
            currentWeight = heading.weight;
            currentNode = newNode;
        }
        return root;
    }

    private static Node getNode(Node node, int level) {
        Node nextNode = null;
        if (node.heading.weight == level) {
            return node;
        } else {
            for (Node child : node.children) {
                nextNode = getNode(child, level);
            }
        }
        return nextNode;
    }
    /////////// END EDITABLE //////////////
}
