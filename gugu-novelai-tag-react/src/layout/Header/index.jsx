import React, {Component} from "react";
import {Layout} from "antd";
import "./index.css";

export default class Header extends Component {
    render() {
        return (
            <Layout.Header className="site-layout-background site-layout-cents">
                GuGu NovelAI Tag
            </Layout.Header>
        );
    }
}
