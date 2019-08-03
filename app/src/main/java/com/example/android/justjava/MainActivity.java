package com.example.android.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkCream=(CheckBox)findViewById(R.id.topping1);
        Boolean addWhippedCream=checkCream.isChecked();
        CheckBox checkChocolate=(CheckBox) findViewById(R.id.toppingChocolate);
        Boolean addChocolate=checkChocolate.isChecked();
        EditText name=(EditText)findViewById(R.id.name);
        String getName=name.getText().toString();
        int price = calculatePrice();
        //String priceMessage="Total: $"+price+"\nThank you!";
        String summary = createOrderSummary(price,addWhippedCream,addChocolate,getName);
        displayMessage(summary);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    public void decrement(View view) {
        if (quantity > 0)
            quantity = quantity - 1;
        displayQuantity(quantity);

    }


    /**
     * Calculates the price of the order.
     *
     *
     * @return returns the price of coffee
     */
    private int calculatePrice() {
        int pricePerCup = 5;
        int price = quantity * pricePerCup;
        return price;
    }

    /**
     * creates order summary
     *
     * @param price   is the price of the cups of coffee ordered
     *
     */
    private String createOrderSummary(int price,Boolean hasWhippedCream, Boolean hasChocolate,String name) {
        String summary = "Name: " + name;
        summary=summary+"\nAdd whipped Cream?"+ hasWhippedCream;
        summary=summary+"\nAdd Chocolate?"+hasChocolate;
        summary=summary+ "\nQuantity: " + quantity + "\nTotal: $" + price
                + "\nThank You!";

        return summary;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }



}
