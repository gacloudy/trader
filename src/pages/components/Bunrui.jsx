import React, { Component } from 'react';

class Bunrui extends Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
      }

      componentDidMount() {
        fetch("http://133.125.50.231:8080/react/bunrui")
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
                        <input type="radio" id={"bunrui" + item[0]} name="bunrui" value={item[0]} checked={(item[0]===this.props.bunrui)?"checked":""} onChange={(e) => { this.props.onChangeBunrui(e.target.value) }}   />
                        <label htmlFor={"bunrui" + item[0]}>{item[1]}　</label>
                    </span>
                )}


                </div>

            </React.Fragment>
        );
    };



}
 
export default Bunrui;



