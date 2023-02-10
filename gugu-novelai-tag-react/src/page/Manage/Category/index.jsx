import {Component} from "react";
import Content from "../../../layout/Content";
import {Form, Input} from "antd";
import Crud from "../../../components/Crud";
import apis from "../../../config/setting";

export default class Category extends Component {

    generateFormContext = () => {
        return (
            <div>
                <Form.Item label="分类名" name="name" rules={[
                    {
                        required: true,
                        message: "Please input category name!",
                    },
                ]}>
                    <Input/>
                </Form.Item>
            </div>
        )
    }

    content = () => {
        const columns = [
            {
                title: "id",
                dataIndex: "id",
                key: "id",
            },
            {
                title: "分类",
                dataIndex: "name",
                key: "name",
            },
        ];
        return (
            <div>
                <Crud url={apis.categoryApi}
                      tabColumns={columns}
                      generateFormContext={this.generateFormContext.bind(this)}
                      failJump
                />
            </div>
        );
    };

    render() {
        return <Content view={this.content()}/>;
    }
}
