import React, { Component } from 'react';

class SelectShare extends Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
      }

      componentDidMount() {
        fetch("http://localhost:8080/react/shares")
          .then(res => res.json())
          .then(
            (result) => {

              this.setState({
                isLoaded: true,
                items: result
              });

              this.props.onChangeCode(result[0].code);
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
                    
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    Share :　
                                </td>
                                <td>
                                    <select onChange={(e) => { this.props.onChangeCode(e.target.value) }} className="form-control" style={{"width": "400px"}}>
                                            {this.state.items.map(item =>
                                            <option key={item.code} value={item.code}>{item.code}:{item.name}</option>
                                            )}

                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>







                </div>
            </React.Fragment>
        );
    };



}
 
export default SelectShare;



