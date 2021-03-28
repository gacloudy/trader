import React, { Component } from 'react';

import { withRouter } from 'react-router';

import Header from './components/Header';
import NowLoading from './components/NowLoading';
import SelectShare from './components/SelectShare';
import Monthly from './components/Monthly';
import TradePattern from './components/TradePattern';
import Bunrui from './components/Bunrui';
import MinCount from './components/MinCount';

import '../css/trader.css';

class TradeResult extends Component {
  state = {
    code: -1,
    loading: false,
    ymFrom: "",
    ymTo: "",
    tradePattern: "buy",
    bunrui: "follow",
    count: 4,
    items: []
};

  handleToHome = () => {
    this.props.history.push('/')
  }

  handleChangeCode = (code) => {
    this.setState({code});
  }

  handleChangeMonthFrom = (ymFrom) => {
    this.setState({ymFrom});
  }
  handleChangeMonthTo = (ymTo) => {
    this.setState({ymTo});
  }

  handleChangeTradePattern = (tradePattern) => {
    this.setState({tradePattern});
  }

  handleChangeBunrui = (bunrui) => {
    this.setState({bunrui});
  }

  handleChangeCount = (count) => {
    console.log(count);
    this.setState({count});
  }

  handleSearch = () => {
    this.setState({loading: true});

    const params = { // 渡したいパラメータをJSON形式で書く
      code: this.state.code,
      ymFrom: this.state.ymFrom,
      ymTo: this.state.ymTo,
      tradePattern: this.state.tradePattern,
      bunrui: this.state.bunrui,
      count: this.state.count
    };
    const query_params = new URLSearchParams(params); 

    fetch("http://133.125.50.231:8080/react/result/search?" + query_params)
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

  render() {
    return (
      <React.Fragment>

        <Header onClick={this.handleToHome} />

        <div style={{"padding": "10px 50px 50px 50px"}}>

            <SelectShare onChangeCode={this.handleChangeCode} />
            <Monthly onChangeMonthFrom={this.handleChangeMonthFrom} onChangeMonthTo={this.handleChangeMonthTo}/>

            <TradePattern tradePattern={this.state.tradePattern} onChangeTradePattern={this.handleChangeTradePattern}/>
            <Bunrui bunrui={this.state.bunrui} onChangeBunrui={this.handleChangeBunrui}/>
            <MinCount  onChangeCount={this.handleChangeCount}/>

            <div style={{"paddingTop": "10px", "paddingBottom": "10px"}}>
                <button  className="btn btn-success" onClick={this.handleSearch}>
                  Search
                </button>
            </div>

            <div style={{height: "500px", overflowY: "scroll"}}>
				<table className="table" id="result_table">
					<thead className="thead-dark">
						<tr>
							<th scope="col">AnalyzeKeyCode</th>
							<th scope="col">Benefit</th>
							<th scope="col">Benefit / Count</th>
							<th scope="col">Count</th>
						</tr>
					</thead>
					<tbody style={{overflowX: "hidden", overflowY: "scroll", height: "100px"}}>
            {this.state.items.map(item =>
                  <tr key={item.analyze_key_code}>
                    <td className="disp-ak">
                    {item.analyze_key_code}
                    </td>
                    <td>
                    {item.sum_bnft}
                    </td>
                    <td>
                    {item.avg_bnft}
                    </td>
                    <td>
                    {item.count}
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



export default withRouter(TradeResult)