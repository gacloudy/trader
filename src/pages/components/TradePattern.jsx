import React, { Component } from 'react';

class TradePattern extends Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
      }

      componentDidMount() {
        fetch("http://133.125.50.231:8080/react/tradePattern")
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

    render() { 
        return ( 
            <React.Fragment>
                <div style={{padding: "5px 2px"}}>

                {this.state.items.map(item =>
                    <span key={item[0]}>
                        <input type="radio" id={"tradePattern" + item[0]} name="tradePattern" value={item[0]} checked={(item[0]===this.props.tradePattern)?"checked":""} onChange={(e) => { this.props.onChangeTradePattern(e.target.value) }}   />
                        <label htmlFor={"tradePattern" + item[0]}>{item[1]}　</label>
                    </span>
                )}


                </div>

            </React.Fragment>
        );
    };



}
 
export default TradePattern;



