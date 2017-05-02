package nefersky.listfragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleListFragment extends ListFragment {
    // определяем массив типа String
    final String[] catNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // 1 3
        //Toast.makeText(getContext(), catNames[position], Toast.LENGTH_SHORT).show();
        //190392

        // 2
        //TextView textView = (TextView) v;
        //String itemText = textView.getText().toString();
        //Toast.makeText(getContext(), itemText, Toast.LENGTH_SHORT).show();

        // 4
        String prompt = "Вы выбрали: " + getListView().getItemIdAtPosition(position) + "\n";
        prompt += "Выбранные элементы: \n";
        int count = getListView().getCount();
        SparseBooleanArray sparseBooleanArray = getListView().getCheckedItemPositions();
        for (int i = 0; i < count; i++) {
            if (sparseBooleanArray.get(i)) {
                prompt += getListView().getItemAtPosition(i).toString() + "\n";
            }
        }
        Toast.makeText(getContext(), prompt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.listfragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 1
        //ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, catNames);

        // 2
        //ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, catNames);

        // 3
        //MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.listfragment_row, catNames);

        // 4
        ListAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_multiple_choice, catNames);

        setListAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<String> {
        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //return super.getView(position, convertView, parent);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_row, parent, false);

            TextView catNameTextView = (TextView) row.findViewById(R.id.textViewName);
            catNameTextView.setText(catNames[position]);

            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageViewIcon);
            iconImageView.setImageResource(R.mipmap.ic_launcher_round);

            return row;
        }
    }
}
