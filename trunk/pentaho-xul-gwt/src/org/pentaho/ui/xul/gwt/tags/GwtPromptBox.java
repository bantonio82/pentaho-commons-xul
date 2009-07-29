package org.pentaho.ui.xul.gwt.tags;

import java.util.ArrayList;
import java.util.List;


import org.pentaho.gwt.widgets.client.buttons.RoundedButton;
import org.pentaho.ui.xul.components.XulPromptBox;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.gwt.GwtXulHandler;
import org.pentaho.ui.xul.gwt.GwtXulParser;
import org.pentaho.ui.xul.util.XulDialogCallback;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwtPromptBox extends GwtMessageBox implements XulPromptBox {

  private TextBox textbox = new TextBox();

  private List<XulDialogCallback<String>> callbacks = new ArrayList<XulDialogCallback<String>>();
  
  private String acceptLabel = "OK";
  
  private String cancelLabel = "Cancel";

  private RoundedButton acceptBtn = new RoundedButton();
  private RoundedButton cancelBtn = new RoundedButton();
  
  
  static final String ELEMENT_NAME = "promptbox"; //$NON-NLS-1$
  

  public static void register() {
    GwtXulParser.registerHandler(ELEMENT_NAME, new GwtXulHandler() {
      public Element newInstance() {
        return new GwtPromptBox();
      }
    });
  }

  public GwtPromptBox() {
    super(ELEMENT_NAME);
    textbox.setWidth("90%");
    textbox.addKeyDownHandler(new KeyDownHandler(){
      public void onKeyDown(KeyDownEvent event) {
        final int keyCode = event.getNativeKeyCode();
        
        switch(keyCode){
          case KeyCodes.KEY_ENTER:
            notifyListenersOnAccept();
            break;
          case KeyCodes.KEY_ESCAPE:
            notifyListenersOnCancel();
            break;
        }
        
      }
    });
    
    //setup default width and height in case user does not specify
    setHeight(150);
    setWidth(275);
    
    acceptBtn.addClickListener(new ClickListener(){
      
      public void onClick(Widget sender) {
        notifyListenersOnAccept();
      }
    });

    cancelBtn.addClickListener(new ClickListener(){
      
      public void onClick(Widget sender) {
        notifyListenersOnCancel();
      }
    });
  }
  
  
  private void notifyListenersOnAccept(){
    hide();
    for(XulDialogCallback<String> callback : callbacks){
      callback.onClose(GwtPromptBox.this, XulDialogCallback.Status.ACCEPT, textbox.getText());
    }
  }
  
  private void notifyListenersOnCancel(){
    hide();
    for(XulDialogCallback<String> callback : callbacks){
      callback.onClose(GwtPromptBox.this, XulDialogCallback.Status.CANCEL, null);
    }
  }

  @Override
  public Panel getButtonPanel() {
    acceptBtn.setText(acceptLabel);
    cancelBtn.setText(cancelLabel);
    
    HorizontalPanel hp = new HorizontalPanel();
    hp.add(acceptBtn);
    hp.setCellWidth(acceptBtn, "100%");
    hp.setCellHorizontalAlignment(acceptBtn, hp.ALIGN_RIGHT);
    hp.add(cancelBtn);
    return hp;
  }

  @Override
  public Panel getDialogContents() {

    VerticalPanel vp = new VerticalPanel();
    Label lbl = new Label(getMessage());
    vp.add(lbl);
    vp.add(textbox);
    vp.setCellHorizontalAlignment(textbox, vp.ALIGN_CENTER);

    return vp;
  }

  public void setAcceptLabel(String lbl){
    this.acceptLabel = lbl;
  }
  
  public void setCancelLabel(String lbl){
    this.cancelLabel = lbl;
  }

  public void addDialogCallback(XulDialogCallback callback) {
    this.callbacks.add(callback);
  }

  public void removeDialogCallback(XulDialogCallback callback) {
    this.callbacks.remove(callback);
  }

  public String getValue() {
    return this.textbox.getValue();
  }

  public void setValue(String value) {
    this.textbox.setValue(value);
  }

  @Override
  public int open() {
    super.show();
    textbox.setFocus(true);
    return 0;
  }
  
  

}
