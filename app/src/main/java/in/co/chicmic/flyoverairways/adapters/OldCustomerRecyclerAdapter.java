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
import in.co.chicmic.flyoverairways.listeners.ChooseUserListener;

public class OldCustomerRecyclerAdapter extends
        RecyclerView.Adapter<OldCustomerRecyclerAdapter.ViewHolder> {
    private List<CustomerDetails> mCustomerDetailsList;
    private ChooseUserListener mListener;

    public OldCustomerRecyclerAdapter(List<CustomerDetails> pCustomerDetailsList){
        mCustomerDetailsList = pCustomerDetailsList;
    }

    public OldCustomerRecyclerAdapter(List<CustomerDetails> pCustomerDetailsList, ChooseUserListener pListner){
        mCustomerDetailsList = pCustomerDetailsList;
        mListener = pListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.old_customer_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        CustomerDetails customerDetails = mCustomerDetailsList.get(position);
        viewHolder.mFullNameTV.setText(customerDetails.getFullName());
        viewHolder.mPassCodeTV.setText(String.valueOf(customerDetails.getPassengerCode()));
        if (mListener != null){
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.viewClickAtPosition(viewHolder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCustomerDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mFullNameTV;
        TextView mPassCodeTV;
        View view;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            mFullNameTV = itemView.findViewById(R.id.tv_full_name);
            mPassCodeTV = itemView.findViewById(R.id.tv_pass_code);
        }
    }
}
