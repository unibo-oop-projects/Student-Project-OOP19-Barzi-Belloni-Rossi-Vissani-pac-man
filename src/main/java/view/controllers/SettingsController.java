package view.controllers;

import java.io.IOException;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import view.GameScene;
import view.View;
import view.utils.SoundManager;
import view.utils.SoundManager.Sound;

public class SettingsController extends SceneController {

    @FXML
    private RadioButton volumeOn;

    @FXML
    private RadioButton volumeOff;

    @FXML
    private ChoiceBox<String> gameMap;

    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        if (SoundManager.getSoundManager().getSoundEnabled()){
            this.volumeOn.setSelected(true);
        }else {
            this.volumeOff.setSelected(true);
        }
    }

    @FXML
    private void onResetRankingClick(final ActionEvent event) throws IOException {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getController().resetRanking();
    }

    @FXML
    void onBackClick(final ActionEvent event) {
        SoundManager.getSoundManager().play(Sound.BUTTON);
        this.getView().setScene(GameScene.MAINMENU);
    }

    @FXML
    void volumeOffSelected(final ActionEvent event) {
        SoundManager.getSoundManager().setSoundEnabled(false);
    }

    @FXML
    void volumeOnSelected(final ActionEvent event) {
        SoundManager.getSoundManager().setSoundEnabled(true);
    }
}
