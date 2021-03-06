package fr.arinonia.component.button;

import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



public class FXButton extends GridPane {

	private Rectangle background = new Rectangle();
	private Label label = new Label();

	private boolean enabled = true;

	private Color color_background;
	private Color color_background_hover;
	private Color color_border;
	private Color color_border_hover;
	private Color color_label;
	private Color color_label_hover;
	private int transition_background;
	private int transition_border;

	public FXButton(Color color_background, Color color_background_hover, Color color_border, Color color_border_hover,
			Color color_label, Color color_label_hover, int transition_background, int transition_border) {
		this.color_background = color_background;
		this.color_background_hover = color_background_hover;
		this.color_border = color_border;
		this.color_border_hover = color_border_hover;
		this.color_label = color_label;
		this.color_label_hover = color_label_hover;

		this.transition_background = transition_background;
		this.transition_border = transition_border;

		init();
	}
	
	private void init() {
		this.setCursor(Cursor.HAND);

		this.background.setFill(this.color_background);
		this.background.setStroke(this.color_border);
		this.background.setStrokeWidth(0);

		GridPane.setHgrow(this.background, Priority.ALWAYS);
		GridPane.setVgrow(this.background, Priority.ALWAYS);

		this.label.setTextFill(this.color_label);
		this.label.setWrapText(true);
		this.label.setAlignment(Pos.CENTER);
		this.label.setStyle("-fx-font-size: 12px; -fx-font-family: 'Roboto';");

		GridPane.setHgrow(this.label, Priority.ALWAYS);
		GridPane.setVgrow(this.label, Priority.ALWAYS);
		GridPane.setHalignment(this.label, HPos.CENTER);
		GridPane.setValignment(this.label, VPos.CENTER);

		this.getChildren().addAll(new Node[] { this.background, this.label });

		FillTransition backgroundTransition = new FillTransition(Duration.millis(this.transition_background),
				this.background, this.color_background, this.color_background_hover);
		FillTransition backgroundExitedTransition = new FillTransition(Duration.millis(this.transition_background),
				this.background, this.color_background_hover, this.color_background);
		StrokeTransition strokeTransition = new StrokeTransition(Duration.millis(this.transition_border),
				this.background, this.color_border, this.color_border_hover);
		StrokeTransition strokeExitedTransition = new StrokeTransition(Duration.millis(this.transition_border),
				this.background, this.color_border_hover, this.color_border);

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				backgroundExitedTransition.stop();
				strokeExitedTransition.stop();
				if (enabled) {
					label.setTextFill(color_label_hover);
					backgroundTransition.play();
					strokeTransition.play();
				}
			}

		});

		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				backgroundTransition.stop();
				strokeTransition.stop();
				if (enabled) {
					label.setTextFill(color_label);
					backgroundExitedTransition.play();
					strokeExitedTransition.play();
				}
			}

		});

	}
	
	public Label getLabel() {
		return this.label;
	}
	
	public void setText(String value) {
		this.label.setText(value);
	}
	
	public String getText() {
		return this.label.getText();
	}

	public void setStrokeWidth(double wight) {
		this.background.setStrokeWidth(wight);
	}
	
	public void setArc(double value) {
		this.background.setArcWidth(value);
		this.background.setArcHeight(value);
	}

	public void setSize(int width, int height) {
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);

		this.label.setMaxWidth(width - 4);
		this.label.setMaxHeight(height - 4);

		this.background.setWidth(width);
		this.background.setHeight(height);
	}
	
	public void setEnabled(boolean value) {
		this.enabled = value;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}

}
