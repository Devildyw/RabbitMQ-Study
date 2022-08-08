package top.devildyw.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Devil
 * @since 2022-08-08-22:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO implements Serializable {
    private static final long serialVersionUID = 142L;

    String email;
}
