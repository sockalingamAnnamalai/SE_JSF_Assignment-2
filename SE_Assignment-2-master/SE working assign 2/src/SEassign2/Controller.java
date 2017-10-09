package SEassign2;

import SEassign2.Datainsert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean(name="Controller")
@SessionScoped
public class Controller {
	
	private String name="";
	private String emailAddress="";
	private String userID="";
	private String password="";
	private UIComponent loginButton;
	
	public String getname() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void nameValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z]*";
		String name = (String) value;
		String nameTrim = name.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) nameTrim);
		if(!matcher.matches() || nameTrim.length() == 0 || nameTrim.length()>15) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid Name");
			context.addMessage(comp.getClientId(context), message);
		}
		
	}
	
	public void emailValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "^(.+)@(.+)$";
		
		String emailAddress = (String) value;
		String emailAddressTrim = emailAddress.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) emailAddressTrim);
		if(!matcher.matches() || emailAddressTrim.length() == 0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid Email Address");
			context.addMessage(comp.getClientId(context), message);
		}
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void UIDValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z0-9]*";
		String userid = (String) value;
		String useridTrim = userid.replaceAll("\\s","");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) useridTrim);
		if(useridTrim.length()>12 || !matcher.matches() || useridTrim.length()==0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Invalid userID. Please enter userId less than 12 characters");
			context.addMessage(comp.getClientId(context), message);
		}
	}
	
	public void passwordValidator(FacesContext context, UIComponent comp, Object value) {
		String regex = "[A-Za-z0-9@#$%^&+=]*";
		String password = (String) value;
		String passwordTrim = password.replaceAll("\\s", "");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) passwordTrim);
		if(passwordTrim.length()>12 || !matcher.matches() || passwordTrim.length()==0) {
			((UIInput) comp).setValid(false);
			
			FacesMessage message = new FacesMessage("Maximum length of userID is 12 characters");
			context.addMessage(comp.getClientId(context), message);
		}
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String RegisterController() {
		
		boolean userInserted = Datainsert.insertUser(this.name, this.emailAddress, this.userID, this.password);
		if(userInserted) {
			FacesContext.getCurrentInstance().addMessage("signInForm:loginButton", new FacesMessage(FacesMessage.SEVERITY_INFO,"User Successfuly Registered", null));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		  return ("successfully_registered.xhtml");
		}
		else
		  return ("unsuccess.xhtml");
	}
	
	public String LoginController() {
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userID);
		
		String userExists = Datainsert.checkUserExists(this.userID,this.password);
		if(userExists != null) {
			this.name = userExists;
			return("success.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage("signInForm:loginButton", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid UserID or Password", null));
			return "unsuccess.xhtml";
		}
	    
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return("home.xhtml");
	}
	
	public void setLoginButton(UIComponent loginButton) {
        this.loginButton = loginButton;
    }

    public UIComponent getLoginButton() {
        return loginButton;
    }

}
