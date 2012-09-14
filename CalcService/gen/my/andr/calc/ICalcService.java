/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\android\\workspace\\CalcService\\src\\my\\andr\\calc\\ICalcService.aidl
 */
package my.andr.calc;
public interface ICalcService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements my.andr.calc.ICalcService
{
private static final java.lang.String DESCRIPTOR = "my.andr.calc.ICalcService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an my.andr.calc.ICalcService interface,
 * generating a proxy if needed.
 */
public static my.andr.calc.ICalcService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof my.andr.calc.ICalcService))) {
return ((my.andr.calc.ICalcService)iin);
}
return new my.andr.calc.ICalcService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_calc:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.calc(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements my.andr.calc.ICalcService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public java.lang.String calc(java.lang.String ssu, java.lang.String sop) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(ssu);
_data.writeString(sop);
mRemote.transact(Stub.TRANSACTION_calc, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_calc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String calc(java.lang.String ssu, java.lang.String sop) throws android.os.RemoteException;
}
