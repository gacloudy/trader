import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import Home from './pages/Home';
import DailyMovement from './pages/DailyMovement';
import Sample from './pages/Sample';


class App extends React.Component {
  render() {
    return (
        <BrowserRouter>
          <div>
            <Switch>
              <Route exact path={'/'} component={Home}/>
              <Route exact path={'/dailyMovement'} component={DailyMovement}/>
              <Route exact path={'/sample'} component={Sample}/>
            </Switch>
          </div>
        </BrowserRouter>
    )
  }
}

export default App


