import {Component} from "react";
import Content from "../../../layout/Content";
import Crud from "../../../components/Crud";
import apis from '../../../config/setting'
import {Button, Form, Input, message, Select, Spin} from "antd";
import {del, get} from "../../../utils/requestUtil";

export default class Attribute extends Component {

    state = {
        spinning: false,
        categoryNameList: [],
        refresh: false,
    }

    UNSAFE_componentWillMount = () => {
        this.initData();
    };

    initData = () => {
        this.setState({spinning: true});
        get(apis.categoryApi).then(json => {
            let data = json.data;
            this.setState({spinning: false, categoryNameList: data});
        }).catch(error => {
            console.error('无法加载分类数据', error);
            message.error("无法加载分类数据");
        });
    }

    generateFormContext = () => {
        const {categoryNameList} = this.state;
        const {Option} = Select;
        let childOption = categoryNameList.map(item => <Option key={item.id} value={item.id}>{item.name}</Option>);
        return (
            <div>
                <Form.Item
                    label="分类名"
                    name="categoryId"
                    rules={[
                        {
                            required: true,
                            message: "Please select categoryName!",
                        },
                    ]}
                >
                    <Select>{childOption}</Select>
                </Form.Item>
                <Form.Item label="元素名" name="name" rules={[
                    {
                        required: true,
                        message: "Please input name!",
                    },
                ]}>
                    <Input/>
                </Form.Item>
                <Form.Item label="元素值" name="value" rules={[
                    {
                        required: true,
                        message: "Please input value!",
                    },
                ]}>
                    <Input/>
                </Form.Item>
            </div>
        )
    }

    addTopComponent = () => {
        const buttonClick = () => {
            this.setState({spinning: true});
            del(apis.attributeApi + '/clean').then(json => {
                const {refresh} = this.state;
                message.success("清理成功");
                this.setState({spinning: false, refresh: !refresh});
            }).catch(error => {
                console.error('无法请求后端', error);
                message.error("无法请求后端");
            });
        }
        return (
            <Button type="primary" danger onClick={buttonClick}>清理重复数据</Button>
        );
    }

    content = () => {
        const {spinning, refresh} = this.state;
        const columns = [
            {
                title: "id",
                dataIndex: "id",
                key: "id",
            },
            {
                title: "所属分类",
                dataIndex: "categoryName",
                key: "categoryName",
            },
            {
                title: "元素名",
                dataIndex: "name",
                key: "name",
            },
            {
                title: "元素值",
                dataIndex: "value",
                key: "value",
            },
        ];
        return (
            <div>
                <Spin spinning={spinning}>
                    <Crud url={apis.attributeApi}
                          tabColumns={columns}
                          generateFormContext={this.generateFormContext.bind(this)}
                          addTopComponent={this.addTopComponent.bind(this)}
                          refresh={refresh}
                          failJump
                    />
                </Spin>
            </div>
        );
    };

    render() {
        return <Content view={this.content()}/>;
    }
}
