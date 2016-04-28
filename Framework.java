//Bolles, Nathan
//Project 2
//CS 110  section 010 SP 15

import java.util.ArrayList;
import java.awt.Label;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class Framework extends Application {
    public static void main(String[] args) {
        Framework.launch(args);
    }
    
    private static final int
    	BAR_COUNT = 16,
    	GEN_MIN = 1,
    	GEN_MAX = 100;
    
    private static final String
		COLOR_PROCESS = "-fx-bar-fill: #eeb111",
		COLOR_INITIAL = "-fx-bar-fill: #ff0000",
    	COLOR_FIRST = "-fx-bar-fill: #00491b",
    	COLOR_BUBBLE = "-fx-bar-fill: #6E6E6E",
    	COLOR_INSERTION = "-fx-bar-fill: #00FFFF",
    	COLOR_SELECTION = "-fx-bar-fill: #FF0040";
    	

    private static final String[] ALGORITHMS = {
    	"Swap Halves",
    	"Unimplemented Example",
    	"Full Reverse",
    	"Bubble Sort",
    	"Selection Sort",
    	"Insertion Sort"
    };
    
    private static int DELAY_MILLIS = 750;
    
    private ArrayList<XYChart.Data<String,Number>> bars;
    
	@Override
    public void start(Stage stage) {
    	stage.setTitle("Animated Algorithms");
    	stage.setWidth(800);
    	stage.setHeight(600);
    	
		final BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
    	
        final BarChart<String,Number> chart = 
            new BarChart<String,Number>(new CategoryAxis(), new NumberAxis(0, GEN_MAX, 0));
        chart.setLegendVisible(false);
		chart.getYAxis().setTickLabelsVisible(false);
		chart.getYAxis().setOpacity(0);
		chart.getXAxis().setTickLabelsVisible(true);
		chart.getXAxis().setOpacity(0);
		chart.setHorizontalGridLinesVisible(true);
		chart.setVerticalGridLinesVisible(true);
 
        XYChart.Series<String,Number> data = new XYChart.Series<String,Number>();
        chart.getData().add(data);
        bars = new ArrayList<XYChart.Data<String,Number>>();
        for (int i = 0; i < BAR_COUNT; i++) {
        	XYChart.Data<String,Number> bar = new XYChart.Data<>(Integer.toString(i), GEN_MIN);
        	bars.add(bar);
        	data.getData().add(bar);
        	bar.getNode().setStyle(COLOR_INITIAL);
        }
        
        final ComboBox<String> myCB = new ComboBox<>();
        	myCB.getItems().setAll("50 milliseconds", "250 milliseconds", "500 milliseconds", "750 milliseconds", "1500 milliseconds");
        	myCB.setValue("750 milliseconds");
        
    	final FlowPane inputs = new FlowPane();
    	inputs.setHgap(5);
    	inputs.setVgap(5);
	    	
    	EventHandler<ActionEvent> randomizer = new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	randomizeAll();
		    }
    	};
    	final Button random = new Button("Randomize");
    	random.setOnAction(randomizer);
    	inputs.getChildren().add(random);
    	
    	EventHandler<ActionEvent> runner = new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	(new Thread() {
    	    		public void run(){
    	    			Platform.runLater(new Runnable() {
    	    				@Override
    	    				public void run() {
    	    					inputs.setDisable(true);
    	    				}
    	    			});
    	    			callBarAlgorithm(((Button) e.getSource()).getText().toString());
    	    			Platform.runLater(new Runnable() {
    	    				@Override
    	    				public void run() {
    	    					inputs.setDisable(false);
    	    				}
    	    			});
    	        	};
    	    	}).start();
    	    }
    	};
    	for (int i = 0; i < ALGORITHMS.length; i++) {
    		final Button algo = new Button(ALGORITHMS[i]);
    		algo.setOnAction(runner);
    		inputs.getChildren().add(algo);
    	}
    	 inputs.getChildren().add(myCB);
    	myCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>(){
    	
//			public void alter(ObservableValue<? extends Object> source, Object oldValue, Object newValue){
//    			
//    			if(newValue.toString().equals("50 milliseconds")) DELAY_MILLIS = 50;
//    			else if(newValue.toString().equals("250 milliseconds")) DELAY_MILLIS = 250;
//    			else if(newValue.toString().equals("500 milliseconds")) DELAY_MILLIS = 500;
//    			else if(newValue.toString().equals("750 milliseconds")) DELAY_MILLIS = 750;
//    			else if(newValue.toString().equals("1500 milliseconds")) DELAY_MILLIS = 1500;	
//    		}

			@Override
			public void changed(ObservableValue<? extends Object> source,
					Object oldValue, Object newValue) {
				
				// TODO Auto-generated method stub
				if(newValue .toString().equals("50 milliseconds")) DELAY_MILLIS = 50;
    			else if(newValue.toString().equals("250 milliseconds")) DELAY_MILLIS = 250;
    			else if(newValue.toString().equals("500 milliseconds")) DELAY_MILLIS = 500;
    			else if(newValue.toString().equals("750 milliseconds")) DELAY_MILLIS = 750;
    			else if(newValue.toString().equals("1500 milliseconds")) DELAY_MILLIS = 1500;
			}

    	});
    	
	    pane.setCenter(chart);
	    pane.setBottom(inputs);
	    
    	stage.setScene(new Scene(pane));
    	stage.show();
    }
    
    public void assign(int index, int value) {
    	bars.get(index).setYValue(value);
    }
    
    public int retrieve(int index) {
    	return (int) bars.get(index).getYValue();
    }
    
    public void randomizeAll() {
    	for (int i = 0; i < BAR_COUNT; i++)
			assign(i, GEN_MIN + (int)(Math.random() * ((GEN_MAX - GEN_MIN) + 1)));
    }
    
    public void paint(int index, String style) {
    	XYChart.Data<String,Number> which = bars.get(index);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				which.getNode().setStyle(style);
			}
		});
    }
    
    public void paintAll(String style) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < BAR_COUNT; i++) {
					paint(i, style);
				}
			}
		});
    }
    
    public void delay() {
		try {
			Thread.sleep(DELAY_MILLIS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void callBarAlgorithm(String name) {
    	if (name.equals("Swap Halves"))
			doSwapHalves();
    	else if (name.equals("Full Reverse"))
    		doFullReverse();
    	else if (name.equals("Bubble Sort"))
    		doBubbleSort();
    	else if (name.equals("Selection Sort"))
    		doSelectionSort();
    	else if (name.equals("Insertion Sort"))
    		doInsertionSort();
    	else
    		System.err.println("Unimplemented: " + name);
    }
    
    public void doSwapHalves() {
    	int half = bars.size()/2;
    	for (int i = 0; i < half; i++) {
    		int j = i + half + (bars.size() % 2 == 1 ? 1 : 0);
    		
    		paint(i, COLOR_PROCESS);
    		paint(j, COLOR_PROCESS);
    		
    		int temp = retrieve(i);
    		assign(i, retrieve(j));
    		assign(j, temp);
    		
    		delay();
    		
    		paint(i, COLOR_INITIAL);
    		paint(j, COLOR_INITIAL);
    	}
    }
    
    public void doFullReverse() {
    	int half = bars.size()/2;
    	for (int i = 0; i < half; i++){
    		int full = bars.size()-1-i + (bars.size() % 2 == 1 ? 1 : 0);
    		
    		paint(i, COLOR_FIRST);
    		paint(full, COLOR_FIRST);
    		
    		int temp = retrieve(i);
    		assign(i, retrieve(full));
    		assign(full, temp);
    		
    		delay();
    		
    		paint(i, COLOR_INITIAL);
    		paint(full, COLOR_INITIAL);
    	}
    }
    
    public void doBubbleSort() {
    	int full = bars.size();
    	for(int i = 0; i < full; i++){
    		for(int j = 0; j < full-1; j++){
    			if( bars.get(j).getYValue().intValue() > bars.get(j+1).getYValue().intValue()){	
    			
    			paint(j, COLOR_BUBBLE);
        		paint(j+1, COLOR_BUBBLE);
        		
        		int temp = retrieve(j);
        		assign(j, retrieve(j+1));
        		assign(j+1, temp);
        		
        		delay();
        		
        		paint(j, COLOR_INITIAL);
        		paint(j+1, COLOR_INITIAL);
    			}
    		}
    	}
    }
    
    public void doSelectionSort(){
    	int full = bars.size();
    	for(int i = 0; i < full; i++){
    		int sel = i;
    		for(int j = i + 1; j < full; j++){
    			if(bars.get(j).getYValue().intValue() < bars.get(sel).getYValue().intValue()){
    				sel = j;
    				
    				paint(i, COLOR_SELECTION);
            		paint(sel, COLOR_SELECTION);
            		
            		delay();
            		
            		paint(i, COLOR_INITIAL);
            		paint(sel, COLOR_INITIAL);
            		
    			}
    		}

    		
    		if( sel != i) {
            		
            		int temp = retrieve(i);
            		assign(i, retrieve(sel));
            		assign(sel, temp);
            		
            		delay();
            		
            		paint(i, COLOR_INITIAL);
            		paint(sel, COLOR_INITIAL);
    		}
    	}
    }
    
    public void doInsertionSort(){
    	int full = bars.size();
    	for( int i = 1; i < full; i++){
    		int value = retrieve(i);
			int j = i-1;
			
			paint(i, COLOR_INSERTION);
    		
			boolean done = false;
			while ( done == false) {
				
				paint(j, COLOR_INSERTION);
				
				if(retrieve(j) > value){
				assign(j+1, retrieve(j));
				
				delay();
				
				j=j-1;
        		
					if(j < 0){
					done = true;
					}
				}
				else done = true;
			assign(j+1, value);
			
			paint(i, COLOR_INITIAL);
    		paint(j+1, COLOR_INITIAL);
		}

    			
//    			paint(i, COLOR_INSERTION);
//        		paint(j+1, COLOR_INSERTION);
        		
//    			int temp = retrieve(j);
//        		assign(j, retrieve(j+1));
//        		assign(j+1, temp);
    		
        		
			paint(i, COLOR_INITIAL);
        		paint(j+1, COLOR_INITIAL);
    		}
    }

}
