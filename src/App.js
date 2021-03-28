import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import Home from './pages/Home';
import DailyMovement from './pages/DailyMovement';
import TradeResult from './pages/TradeResult';
import Log from './pages/Log';
import Sample from './pages/Sample';


class App extends React.Component {
  render() {
    return (
        <BrowserRouter>
          <div>
            <Switch>
              <Route exact path={'/'} component={Home}/>
              <Route exact path={'/dailyMovement'} component={DailyMovement}/>
              <Route exact path={'/tradeResult'} component={TradeResult}/>
              <Route exact path={'/sample'} component={Sample}/>
              <Route exact path={'/log'} component={Log}/>
            </Switch>
          </div>
        </BrowserRouter>
    )
  }
}

export default App


