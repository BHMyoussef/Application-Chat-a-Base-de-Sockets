package ma.chatui.chatui;

import javafx.event.ActionEvent;

public class SignIn {

    public void SignInHandler(ActionEvent event){
        ScenesController scenesController = new ScenesController();
        scenesController.switchScene(event, "/SignUpUi.fxml");
    }
}
