import {Component} from "react";
import {Layout} from "antd";

export default class Content extends Component {
    render() {
        return (
            <Layout.Content>
                <div
                    className="site-layout-background"
                    style={{
                        margin: 20,
                        padding: 10,
                    }}
                >
                    {this.props.view}
                </div>
            </Layout.Content>
        );
    }
}
