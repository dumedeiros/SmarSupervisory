/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FromControle;

import br.ufrn.lii.commonsdomain.process.TagItem;
import java.io.Serializable;

/**
 *
 * @author eduardomedeiros
 */
public class TagItemm extends TagItem implements Serializable, Comparable<TagItem> {

    public static final long serialVersionUID = 123;
    private Integer id;
    private String idStr;
    private String name;
    private String description;
    private boolean children;
    private boolean node;
    private boolean canRead;
    private boolean canWrite;

   

    public int compareTo(TagItem o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
