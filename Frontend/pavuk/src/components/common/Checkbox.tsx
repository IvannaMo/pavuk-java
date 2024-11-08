import { Field } from "formik";


function Checkbox(props: any) {
  return (
    <Field name={props.name}>
      {({ field, form }: { field: any, form: any }) => (
        <label>
          <input
            {...field}
            type="checkbox"
            checked={field.value}
            onChange={() => {
              form.setFieldValue(field.name, !field.value);
              form.setFieldTouched(field.name, true);
            }}
          />
          {props.value}
        </label>
      )}
    </Field>
  );
}

export default Checkbox;