package ${rootPackage};

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.ui.typography.Paragraph;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        var layout = Layout.create("Domino-ui starter")
                .show(ColorScheme.BLUE);

        layout.getLeftPanel()
                .appendChild(Tree.create("Menu")
                        .appendChild(TreeItem.create("Menu 1", Icons.ALL.widgets()))
                        .appendChild(TreeItem.create("Menu 2", Icons.ALL.widgets()))
                        .addItemClickListener(treeItem -> {
                            layout.setContent(Card.create(treeItem.getValue())
                                    .appendChild(Paragraph.create("Welcome to domino-ui , you are viewing "+treeItem.getValue()+" content")));
                        })
                );
    }
}
