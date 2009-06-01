package org.pentaho.ui.xul.gwt.tags;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.containers.XulVbox;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.gwt.AbstractGwtXulComponent;
import org.pentaho.ui.xul.gwt.AbstractGwtXulContainer;
import org.pentaho.ui.xul.gwt.GwtXulHandler;
import org.pentaho.ui.xul.gwt.GwtXulParser;
import org.pentaho.ui.xul.util.Orient;

import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtVbox extends AbstractGwtXulContainer implements XulVbox {

  static final String ELEMENT_NAME = "vbox"; //$NON-NLS-1$
  
  public static void register() {
    GwtXulParser.registerHandler(ELEMENT_NAME, 
    new GwtXulHandler() {
      public Element newInstance() {
        return new GwtVbox();
      }
    });
  }
  
  public GwtVbox() {
    super(ELEMENT_NAME);
    this.orientation = Orient.VERTICAL;
    managedObject = container = new VerticalPanel();
    ((VerticalPanel) container).setStyleName("vbox");
  }


  public void adoptAttributes(XulComponent component) {
    super.adoptAttributes(component);
    setFlex(component.getFlex());
    setHeight(component.getHeight());
    setWidth(component.getWidth());
    
  }

  @Override
  public void setHeight(int height) {
    super.setHeight(height);
    container.setHeight(height+"px");
  }

  @Override
  public void setWidth(int width) {
    super.setWidth(width);
    container.setWidth(width+"px");
  }
  @Override  
  public void setBgcolor(String bgcolor) {
    if(container != null) {
      container.getElement().getStyle().setProperty("backgroundColor", bgcolor);
    }
  }

}
