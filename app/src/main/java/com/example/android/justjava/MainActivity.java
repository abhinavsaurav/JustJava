package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkCream = (CheckBox) findViewById(R.id.topping1);
        boolean addWhippedCream = checkCream.isChecked();
        CheckBox checkChocolate = (CheckBox) findViewById(R.id.toppingChocolate);
        boolean addChocolate = checkChocolate.isChecked();
        EditText name = (EditText) findViewById(R.id.name);
        String getName = name.getText().toString();
        int price = calculatePrice(addWhippedCream, addChocolate);
        String summary = createOrderSummary(price, addWhippedCream, addChocolate, getName);
//        displayMessage(summary);
        String subject = "Just java app for " + getName;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));   //using this only email app should be handling it
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Maximum Cups reached", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;

        displayQuantity(quantity);

    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "Minimum Cups reached", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);

    }


    /**
     * Calculates the price of the order.
     *
     * @return returns the price of coffee
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int pricePerCup = 5;
        int whippedCreamprice = 1;
        int chocolatePrice = 2;
        if (hasWhippedCream)
            pricePerCup = pricePerCup + whippedCreamprice;
        if (hasChocolate)
            pricePerCup = pricePerCup + chocolatePrice;
        int price = quantity * pricePerCup;
        return price;
    }

    /**
     * creates order summary
     *
     * @param price is the price of the cups of coffee ordered
     * @param hasChocolate check if chocolate is added
     * @param hasWhippedCream check if hippedcream has been added
     * @param name name of the customer which has been inputted
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String summary = getString(R.string.order_summary_name, name);
        summary = summary + "\nAdd whipped Cream?" + hasWhippedCream;
        summary = summary + "\nAdd Chocolate?" + hasChocolate;
        summary = summary + "\nQuantity: " + quantity + "\nTotal: $" + price
                + "\n" + getString(R.string.thank_you);

        return summary;
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }


}
