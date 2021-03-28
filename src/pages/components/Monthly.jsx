import React, { Component } from 'react';
 
import '../../css/trader.css';




class Monthly extends Component {
  constructor(props) {
    super(props);

    var date = new Date();
    var dateOneYearAgo = new Date(date.getFullYear() - 1, date.getMonth() + 1, date.getDate());

    const ymFrom = "" + (dateOneYearAgo.getFullYear()) + ((dateOneYearAgo.getMonth() + 1) < 10? ("0"+(dateOneYearAgo.getMonth() + 1)) : (dateOneYearAgo.getMonth() + 1));
    const ymTo = "" + (date.getFullYear()) + ((date.getMonth() + 1) < 10? ("0"+(date.getMonth() + 1)) : (date.getMonth() + 1));
    this.state = {
      error: null,
      isLoaded: false,
      items: [],
      ymFrom:ymFrom,
      ymTo:ymTo
    };


    this.props.onChangeMonthFrom(ymFrom);
    this.props.onChangeMonthTo(ymTo);
  }

  componentDidMount() {
    fetch("http://133.125.50.231:8080/react/ymArry")
      .then(res => res.json())
      .then(
        (result) => {

          this.setState({
            isLoaded: true,
            items: result
          });
        },
        // 補足：コンポーネント内のバグによる例外を隠蔽しないためにも
        // catch()ブロックの代わりにここでエラーハンドリングすることが重要です
        (error) => {
            console.log(error);
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }      

  handleChangeMonthFrom = (ymFrom) => {
    this.setState({ymFrom});
    this.props.onChangeMonthFrom(ymFrom);
  }
  handleChangeMonthTo = (ymTo) => {
    this.setState({ymTo});
    this.props.onChangeMonthTo(ymTo);
  }



  render() {
    return (
      <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>

<table>
                <tbody>
                  <tr>
                    <td>
                      Term :　
                    </td>
                    <td>

                    <select value={this.state.ymFrom} className="form-control" style={{"width": "120px"}}
                      onChange={(e) => { this.handleChangeMonthFrom(e.target.value);}}
                    >
                            {this.state.items.map(item =>
                            <option key={item[0] + "_1"} value={item[0]}  >
                              {item[1]}
                              </option>
                            )}
                    </select>

                    </td>
                  <td>
                　  ～　
                  </td>
                  <td>
                  <select value={this.state.ymTo} className="form-control" style={{"width": "120px"}}
                      onChange={(e) => { this.handleChangeMonthTo(e.target.value);}}
                      >
                    
                          {this.state.items.map(item =>
                            <option key={item[0] + "_2"} value={item[0]} >
                            {item[1]}
                            </option>
                        )}
                  </select>

                  </td>
              </tr>
              </tbody>
              </table>
      </div>

    );
  }
}
export default Monthly;