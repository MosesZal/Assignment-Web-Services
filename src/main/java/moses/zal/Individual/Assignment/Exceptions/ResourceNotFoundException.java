package moses.zal.Individual.Assignment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  private String resourceName;
  private String field;
  private Object value;

  public ResourceNotFoundException(String resourceName, String field, Object value){
    super(String.format("Process failed! The %s with a(n) %s of \"%s\" could not be found!", resourceName, field, value));
    this.resourceName = resourceName;
    this.field = field;
    this.value = value;
  }

  public String getResourceName(){
    return resourceName;
  }

  public String getField(){return field;}

  public Object getValue(){return value;}
}

