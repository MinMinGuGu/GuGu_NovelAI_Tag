import {Component} from "react";
import {renderRoutes} from "react-router-config";
import routers from "../config/router";

export default class Routers extends Component {
    render() {
        return renderRoutes(routers);
    }
}
