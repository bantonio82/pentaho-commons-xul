package org.pentaho.ui.xul.gwt.tags;


import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.containers.XulBox;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.gwt.AbstractGwtXulComponent;
import org.pentaho.ui.xul.gwt.AbstractGwtXulContainer;
import org.pentaho.ui.xul.gwt.GwtXulHandler;
import org.pentaho.ui.xul.gwt.GwtXulParser;
import org.pentaho.ui.xul.util.Align;
import org.pentaho.ui.xul.util.Orient;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtBox extends AbstractGwtXulContainer implements XulBox {

  static final String ELEMENT_NAME = "box"; //$NON-NLS-1$
  
  public static void register() {
    GwtXulParser.registerHandler(ELEMENT_NAME, 
    new GwtXulHandler() {
      public Element newInstance() {
        return new GwtBox();
      }
    });
  }
  
  protected com.google.gwt.user.client.ui.Panel box;

  public GwtBox() {
    super("box");
    orientation = Orient.HORIZONTAL;
    updateOrientation();
  }
  
  private void updateOrientation() {
    if (getOrientation() == Orient.HORIZONTAL) {
      box = new HorizontalPanel();
    } else {
      box = new VerticalPanel();
    }
    managedObject = box;
  }
  
  public void setOrient(String orient) {
    super.setOrient(orient);
    updateOrientation();
  }

  public void adoptAttributes(XulComponent c) {
    
        // TODO Auto-generated method stub 
      
  }
  
}