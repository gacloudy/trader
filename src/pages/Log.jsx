import React, { Component } from 'react';

import { withRouter } from 'react-router';

import Header from './components/Header';
import NowLoading from './components/NowLoading';

import '../css/trader.css';

class Log extends Component {
  constructor(props) {
    super(props);
    this.state = {
      log: "memory",
      items: [],
      loading: false
    };

    this.handleRefresh();
  }

  

  handleToHome = () => {
    this.props.history.push('/')
  }

  handleChangeLog = (log) => {
    //this.setState({log});
    this.state.log = log;

    this.handleRefresh();
  }

  
  handleRefresh = () => {
    this.setState({loading: true});

    const params = { // 渡したいパラメータをJSON形式で書く
      log: this.state.log
    };
    const query_params = new URLSearchParams(params); 

    fetch("http://133.125.50.231:8080/react/log?" + query_params)
    .then(res => res.json())
    .then(
      (result) => {

        this.setState({
          isLoaded: true,
          items: result
        });
        setTimeout(() => {this.setState({loading: false});}, 500);
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
        setTimeout(() => {this.setState({loading: false});}, 500);

      }
    );
  }

  handleDelete = () => {
    this.setState({loading: true});

    const params = { // 渡したいパラメータをJSON形式で書く
      log: this.state.log
    };
    const query_params = new URLSearchParams(params); 


    fetch("http://133.125.50.231:8080/react/log/delete?" + query_params, {
      method: 'DELETE'
    })
    .then(res => res.json())
    .then(
      (result) => {
        this.setState({
          isLoaded: true,
          items: []
        });
        setTimeout(() => {this.setState({loading: false});}, 500);
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
        setTimeout(() => {this.setState({loading: false});}, 500);

      }
    );
  }
  render() {
    return (
      <React.Fragment>

        <Header onClick={this.handleToHome} />

        <div style={{"padding": "10px 50px 50px 50px"}}>

        <div>
          <select className="form-control" style={{"width": "400px"}} onChange={(e) => { this.handleChangeLog(e.target.value) }}>
            <option value="memory">Memory</option>
            <option value="error">Error Log</option>

          </select>

          </div>

          <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>
                <button  className="btn btn-success"  onClick={this.handleRefresh}>
                  Refresh
                </button>
                　　
                <button className="btn btn-danger"  onClick={this.handleDelete}>
                  Delete
                </button>
          </div>


          <div style={{height: "500px", overflowY: "scroll"}}>
            <table className="table" >
              <thead className="thead-dark">
                <tr>
                  <th scope="col">Date</th>
                  <th scope="col">Log</th>
                </tr>
              </thead>
              <tbody style={{overflowX: "hidden", overflowY: "scroll", height: "100px"}}>
                {this.state.items.map(item =>
                      <tr key={item[0] + item[1]}>
                        <td >
                        {item[0]}
                        </td>
                        <td>
                        {item[1]}
                        </td>
                      </tr>
                    )}

              </tbody>
            </table>
          </div>

          {this.state.loading &&
          <NowLoading open={this.state.loading} />
          }
        </div>

        </React.Fragment>

    )
  }
}



export default withRouter(Log)