import React, { Component } from 'react';

class MinCount extends Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
      }

    render() { 
        return ( 
            <React.Fragment>
                <div style={{padding: "5px 2px"}}>
                <table>
					<tbody>
						<tr>
							<td>
								Minimum Trade Count / Month :　
							</td>
							<td>
								<select id="minCount" name="minCount" defaultValue={"4"} className="form-control" style={{width: "200px"}} onChange={(e) => { this.props.onChangeCount(e.target.value) }}>
									<option value="0" >指定なし</option>
									<option value="1" >1回</option>
									<option value="2" >2回</option>
									<option value="3" >3回</option>
									<option value="4" >4回</option>
									<option value="5" >5回</option>
									<option value="6" >6回</option>
									<option value="7" >7回</option>
									<option value="8" >8回</option>
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
 
export default MinCount;



