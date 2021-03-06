/**
 * 
 */
package grapheus.persistence.model.graph;

import com.arangodb.entity.DocumentField;
import com.arangodb.entity.DocumentField.Type;
import grapheus.persistence.model.annotation.Entity;
import grapheus.persistence.storage.graph.ExternalCompositeId;
import grapheus.persistence.storage.graph.GraphNameUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * @author black
 */
@Entity(name = "artifacts_link")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersistentEdge {
    public static final String FIELD_FROM = "_from";
    public static final String FIELD_TO = "_to";
    public static final String FIELD_KEY = "_key";

    @DocumentField(Type.ID)
    private String id;

    @DocumentField(Type.KEY)
    private String key;

    @DocumentField(Type.REV)
    private String revision;

    @DocumentField(Type.FROM)
    @NonNull
    private String from;

    @DocumentField(Type.TO)
    @NonNull
    private String to;

    private List<String> tags;

    public static PersistentEdge create(String graphName, String fromKey, String targetKey) {
        String vertexCollectionName = GraphNameUtils.verticesCollectionName(graphName);
        return PersistentEdge.builder()
                .from(vertexCollectionName + "/" + ExternalCompositeId.from(fromKey))
                .to(vertexCollectionName + "/" + ExternalCompositeId.from(targetKey))
                .build();
    }
}
