package com.codepath.danbuscaglia.dbtodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.codepath.danbuscaglia.dbtodo.models.PriorityLevel;
import com.codepath.danbuscaglia.dbtodo.models.TodoItemTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by danbuscaglia on 8/13/15.
 */
public class EditTodoFragment extends DialogFragment {

    private TodoItemTask item;
    private static final DateFormat dueDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    private MainActivity activity;

    private TextView txt_EditName;
    private ImageButton btn_Edit_Date;
    private TextView txt_ItemDate;
    private Button btn_Save;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_todo_layout, container, false);
        view = initEditDialog(view);
        return view;
    }

    private View initEditDialog(View view) {
        Bundle bundle = this.getArguments();
        item = (TodoItemTask) bundle.getSerializable("task");
        activity = (MainActivity) this.getActivity();
        setTaskName(view);
        initDatePicker(view);
        setPrioritySpinner(view);
        setupSaveBjutton(view);
        return view;
    }

    private void setupSaveBjutton(View view) {
        btn_Save = (Button) view.findViewById(R.id.btn_save);
        btn_Save.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }});
    }

    private void closeDialog() {
        item.name = txt_EditName.getText().toString();
        item.save();
        activity.refreshLV();
        this.dismiss();
    }

    private void setPrioritySpinner(View view) {
        ArrayAdapter<PriorityLevel> priorityChoices = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, PriorityLevel.values());
        priorityChoices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner priority = (Spinner) view.findViewById(R.id.sp_priority);
        priority.setAdapter(priorityChoices);
        priority.setSelection(priorityChoices.getPosition(item.priority));
        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PriorityLevel p = (PriorityLevel) parent.getItemAtPosition(position);
                item.priority = p;
                item.save();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Leave the priority as it was
            }
        });
    }

    private void setTaskName(View view) {
        txt_EditName = (TextView) view.findViewById(R.id.et_taskname);
        txt_EditName.setText(item.name);
    }

    private void initDatePicker(View view) {
        btn_Edit_Date = (ImageButton) view.findViewById(R.id.btn_editdate);
        txt_ItemDate = (TextView) view.findViewById(R.id.txtDate);
        onChangeTaskDate(item.task_date);
        btn_Edit_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDateChangeRequested();
            }
        });
    }

    private void onDateChangeRequested() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(item.task_date);

        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        GregorianCalendar taskdate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        onChangeTaskDate(taskdate.getTime());
                    }
        }, year, monthOfYear, dayOfMonth);

        datePicker.show();
    }

    private void onChangeTaskDate(Date taskdate) {
        item.task_date = taskdate;
        txt_ItemDate.setText(dueDateFormat.format(taskdate));
        item.save();
    }

    private void onChangePriority(PriorityLevel priority) {
        item.priority = priority;
        item.save();
    }

    private void onChangeTaskName(String name) {
        item.name = name;
        txt_EditName.setText(name);
        item.save();
    }

    public EditTodoFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public interface OnTodoUpdatedListener {
        void onTodoUpdated(TodoItemTask item);
    }
}
