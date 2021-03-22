import React, { Component } from 'react';

import { withRouter } from 'react-router';

import Header from './components/Header';
import NowLoading from './components/NowLoading';
import SelectShare from './components/SelectShare';
import DateCalendar from './components/DateCalendar';
import DailyChart from './components/DailyChart';
import DailyChartButton from './components/DailyChartButton';

import '../css/trader.css';

import Modal from 'react-modal';
Modal.setAppElement('#root')

const customStyles = {
  overlay: {
    zIndex: "100",
    backgroundColor: "rgba(0, 0, 0, 0.5)"
  },
  content: {
    left: "25%",
    width: "50%"
  }
};

class DailyMovement extends Component {
  state = {
    code: -1,
    dateFrom: "",
    dateTo: "",
    modalIsOpen: false,
    loading: false,
    items: [],
    labels: [16, 42, 117.5, 90.5, 120.5, 225, 193, 110, 197, 529.5, 156.5, 76.5, 100, 200],
    prices:[]

};



  handleToHome = () => {
    this.props.history.push('/')
  }


  handleChangeCode = (code) => {

    this.setState({code});
    // console.log(this.state.code);

  }

  handleChangeDate = (dateFrom, dateTo) => {

    // console.log(dateFrom);
    // console.log(dateTo);

    let dateFromVal = "";
    if(dateFrom !== null) {
      dateFromVal = dateFrom.getFullYear() + '/' + (dateFrom.getMonth() < 9 ? '0':'') + (dateFrom.getMonth() + 1) + '/'  + (dateFrom.getDate() < 10 ? '0':'') + dateFrom.getDate();
    }
    let dateToVal = "";
    if(dateTo !== null) {
      dateToVal = dateTo.getFullYear() + '/' + (dateTo.getMonth() < 9 ? '0':'') + (dateTo.getMonth() + 1) + '/'  + (dateTo.getDate() < 10 ? '0':'') + dateTo.getDate();
    }



    this.setState({dateFrom: dateFromVal});
    this.setState({dateTo: dateToVal});

    // console.log(this.state.dateFrom);
    // console.log(this.state.dateTo);


  }

  handleSearch = () => {
    this.setState({loading: true});

    const params = { // 渡したいパラメータをJSON形式で書く
      code: this.state.code,
      dateFrom: this.state.dateFrom,
      dateTo: this.state.dateTo,
    };
    const query_params = new URLSearchParams(params); 

    fetch("http://localhost:8080/react/history/search?" + query_params)
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
          items: [],
          error
        });
      }
    );

    setTimeout(() => {this.setState({loading: false});}, 500);

  }


  handleChart= (code, date) => {

    const query_params = new URLSearchParams({code:code, date:date}); 


    fetch("http://localhost:8080/react/chart?" + query_params)
    .then(res => res.json())
    .then(
      (result) => {

        console.log(result.labels);
        this.setState({
          isLoaded: true,
          labels: result.labels,
          prices: result.prices
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
    );








    this.setState({modalIsOpen: true});

  }

  closeModal = () => {

    this.setState({modalIsOpen: false});
  }


  render() {
    return (
      <React.Fragment>

        <Header onClick={this.handleToHome} />

        <div style={{"padding": "10px 50px 50px 50px"}}>

            <SelectShare onChangeCode={this.handleChangeCode} />
            <DateCalendar  onChangeDate={this.handleChangeDate} />


            <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>
                <button  className="btn btn-success" onClick={this.handleSearch}>
                  Search
                </button>
            </div>

            <div style={{height: "500px", overflowY: "scroll"}}>
            <table className="table" >
              <thead className="thead-dark">
                <tr>
                  <th scope="col">Date</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody style={{height: "100px", overflowX: "hidden", overflowY: "scroll"}}>
                {this.state.items.map(item =>
                  <tr key={item.date}>
                    <td>
                    {item.date}
                    </td>
                    <td>
                    <DailyChartButton code={item.code} date={item.date} open={this.state.modalIsOpen} onCloseModal={this.closeModal}/>

                    </td>
                  </tr>
                )}


                
              </tbody>
            </table>
          </div>
        </div>

        <DailyChart open={this.state.modalIsOpen} onCloseModal={this.closeModal}
        labels={this.state.labels} prices={this.state.prices}
        
        
        />

        {this.state.loading &&
        <NowLoading open={this.state.loading} />

        }

        </React.Fragment>

    )
  }
}



export default withRouter(DailyMovement)