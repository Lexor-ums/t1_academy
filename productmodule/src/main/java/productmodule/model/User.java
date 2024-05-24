package productmodule.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author YStepanov
 */
@Getter
@Builder
@EqualsAndHashCode
public class User {
    private Long id;
    private String username;
}
