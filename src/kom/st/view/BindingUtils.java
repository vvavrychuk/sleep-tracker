package kom.st.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableStringValue;

public class BindingUtils {
  public static StringBinding trim(ObservableStringValue value) {
    return Bindings.createStringBinding(() -> value.get().trim(), value);
  }
}
