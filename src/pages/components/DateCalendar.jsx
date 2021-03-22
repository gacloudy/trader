import React, { Component } from 'react';
import DatePicker, { registerLocale } from "react-datepicker";
import ja from "date-fns/locale/ja";
import "react-datepicker/dist/react-datepicker.css";
 
import '../../css/trader.css';


registerLocale("ja", ja);

class DateCalendar extends Component {
  constructor(props) {
    super(props);

    var date = new Date();
    this.state = {
      dateFrom: new Date(date.getFullYear(), date.getMonth() - 1, date.getDate()),
      dateTo: new Date()
    }

    // console.log(new Date());

    this.props.onChangeDate(new Date(date.getFullYear(), date.getMonth() - 1, date.getDate()), new Date());

  }
  getFormatDate = (date, isFrom) => {

    let dateFromVal = this.state.dateFrom;
    let dateToVal = this.state.dateTo;




    if(isFrom) {
      this.setState({dateFrom: date});
      dateFromVal = date;
      if(date > this.state.dateTo) {
        this.setState({dateTo: date});
        dateToVal = date;
      }
    } else {
      this.setState({dateTo: date});
      dateToVal = date;
      if(this.state.dateFrom > date) {
        this.setState({dateFrom: date});
        dateFromVal = date;
      }
    }

    this.props.onChangeDate(dateFromVal, dateToVal);
  }

  render() {
    return (
      <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>





<table>
                <tbody>
                  <tr>
                    <td>
                      Date :　
                    </td>
                    <td>

                    <DatePicker
                      dateFormat="yyyy/MM/dd"
                        locale="ja"
                        selected={this.state.dateFrom}
                        onChange={(e) => { this.getFormatDate(e, true);}}
                      />

                    </td>
                  <td>
                　  ～　
                  </td>
                  <td>
                  <DatePicker
                      dateFormat="yyyy/MM/dd"
                        locale="ja"
                        selected={this.state.dateTo}
                        onChange={(e) => { this.getFormatDate(e, false);}}
                      />
                  </td>
              </tr>
              </tbody>
              </table>



</div>







    );
  }
}
export default DateCalendar;