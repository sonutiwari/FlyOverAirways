package in.co.chicmic.flyoverairways.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;

public class OldCustomerRecyclerAdapter extends
        RecyclerView.Adapter<OldCustomerRecyclerAdapter.ViewHolder> {
    List<CustomerDetails> mCustomerDetailsList;

    public OldCustomerRecyclerAdapter(List<CustomerDetails> pCustomerDetailsList){
        mCustomerDetailsList = pCustomerDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.old_customer_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CustomerDetails customerDetails = mCustomerDetailsList.get(position);
        viewHolder.mFullNameTV.setText(customerDetails.getFullName());
        viewHolder.mPassCodeTV.setText(String.valueOf(customerDetails.getPassengerCode()));
    }

    @Override
    public int getItemCount() {
        return mCustomerDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mFullNameTV;
        TextView mPassCodeTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mFullNameTV = itemView.findViewById(R.id.tv_full_name);
            mPassCodeTV = itemView.findViewById(R.id.tv_pass_code);
        }
    }
}
