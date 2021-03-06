package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.util.JdbcUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 相同数据库操作父类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 10:49 上午
 */
public abstract class BaseDao<T> {

    private Class<T> type;

    public BaseDao() {
        Class clazz = this.getClass();
        ParameterizedType superclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] arguments = superclass.getActualTypeArguments();
        this.type = (Class<T>) arguments[0];
    }

    public T query(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSetMetaData metaData = statement.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }

            ResultSet resultSet = statement.executeQuery();

            // 判断是否查出数据
            if (resultSet.next()) {
                T t = type.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object object = resultSet.getObject(i + 1);

                    if (object != null) {
                        String columnLabel = metaData.getColumnLabel(i + 1);

                        Field field = null;
                        try {
                            field = type.getDeclaredField(columnLabel);
                        } catch (Exception e) {
                            field = type.getSuperclass().getDeclaredField(columnLabel);
                        }
                        field.setAccessible(true);
                        field.set(t, object);
                    }
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    public List<T> queryList(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSetMetaData metaData = statement.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }

            ResultSet resultSet = statement.executeQuery();

            // 判断是否查出数据
            if (resultSet.next()) {
                ArrayList<T> list = new ArrayList<>();
                do {
                    T t = type.newInstance();

                    for (int i = 0; i < columnCount; i++) {
                        Object object = resultSet.getObject(i + 1);

                        String columnLabel = metaData.getColumnLabel(i + 1);

                        Field field = null;
                        try {
                            field = type.getDeclaredField(columnLabel);
                        } catch (Exception e) {
                            // 当前类不存在该字段，从父类获取
                            field = type.getSuperclass().getDeclaredField(columnLabel);
                        }
                        field.setAccessible(true);

                        if ("file".equals(columnLabel)) {
                            field.set(t, new SerialBlob((byte[]) object));
                        } else {
                            field.set(t, object);
                        }
                    }

                    list.add(t);
                } while (resultSet.next());

                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    protected int insert(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.execute();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return 0;
    }

    protected boolean delete(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return false;
    }

    protected int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }
}
