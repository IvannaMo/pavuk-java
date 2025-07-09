import { useField } from "formik";
import DateView from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./DatePicker.css";


function DatePicker({ className = "", name = "", id = "" }) {
  const [field, meta, helpers] = useField(name);

  const { value } = meta;
  const { setValue } = helpers;

  return (
    <DateView
      {...field}
      selected={value}
      dateFormat="dd/MM/yyyy"
      onChange={(date) => setValue(date)}
      className={`${className}`}
      id={id}
    />
  );
}

export default DatePicker;