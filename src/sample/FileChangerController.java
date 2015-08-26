package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileChangerController {

    private NameChanger nameChanger;

    @FXML
    private ListView listFiles;
    @FXML
    private TextField txtFldPath, txtFldIdentifier, txtFldPartToDelete, txtFldNewName;

    @FXML
    private void initialize() {
        listFiles.setEditable(false);
        reset();
    }

    @FXML
    private void handleAccept() {

        String newName = txtFldNewName.getText();
        String identifier = txtFldIdentifier.getText();
        String deletePart = txtFldPartToDelete.getText();

        if (newName.replaceAll(" ", "").length() > 0) {
            if (identifier.replaceAll(" ", "").length() <= 0 && deletePart.replaceAll(" ", "").length() <= 0) {
                nameChanger.changeAll(newName);
            } else if (identifier.replaceAll(" ", "").length() > 0) {
                nameChanger.changeAllWithIdentifier(identifier, newName);
            }
        } else if (deletePart.replaceAll(" ", "").length() > 0) {
            nameChanger.deletePartOfName(deletePart);
        } else {
            return;
        }

        reset();
        listFiles.setItems(FXCollections.observableArrayList(getFileNames(nameChanger.getFiles())));
    }

    @FXML
    private void handlePath(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File folder = directoryChooser.showDialog(txtFldIdentifier.getScene().getWindow());
        if (folder != null) {
            txtFldPath.setText(folder.getAbsolutePath());
            nameChanger = new NameChanger(folder);

            listFiles.setItems(FXCollections.observableArrayList(getFileNames(nameChanger.getFiles())));
        }
    }

    private void reset() {
        txtFldIdentifier.clear();
        txtFldNewName.clear();
        txtFldPartToDelete.clear();
        listFiles.setItems(FXCollections.observableArrayList());
    }

    private List<String> getFileNames(File[] files) {
        List<String> listNames = new ArrayList<>();

        for (File f : nameChanger.getFiles()) {
            listNames.add(f.getName());
        }
        return listNames;
    }
}
