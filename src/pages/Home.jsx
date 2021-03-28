import React, { Component } from 'react';

import { withRouter } from 'react-router';

class Home extends React.Component {
  state = {
    menus :[
      {name:"Daily Movement", path:"/dailyMovement"},
      {name:"Trade Result", path:"/tradeResult"},
      {name:"Application Log", path:"/log"}
    ]

};

handleLink = (path) => {
  this.props.history.push(path);
}


  render() {
    return (
      <div style={{"padding": "50px"}}>

        <table className="table table-striped">
          <tbody>

          {this.state.menus.map(menu =>
            <tr key={menu.path}>
              <td>
              {menu.name}
              </td>
              <td>
                <button className="btn btn-primary" onClick={() => this.handleLink(menu.path)}>
                  →
                </button>
              </td>
            </tr>		
          )}
	

          </tbody>
        </table>


      </div>
    )
  }
}

export default withRouter(Home)